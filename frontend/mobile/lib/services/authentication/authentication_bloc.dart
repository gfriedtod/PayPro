import 'dart:developer';

import 'package:bloc/bloc.dart';
import 'package:dio/dio.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:get_storage/get_storage.dart';
import 'package:pay_pro_app/model/login_request.dart';
import 'package:pay_pro_app/model/login_response.dart';

import '../../repository/authentication_repository.dart';

part 'authentication_event.dart';
part 'authentication_state.dart';
part 'authentication_bloc.freezed.dart';

class AuthenticationBloc
    extends Bloc<AuthenticationEvent, AuthenticationState> {
  final AuthenticationRepository authenticationRepository;
  AuthenticationBloc({required this.authenticationRepository})
      : super(const AuthenticationState.initial()) {
    on<AuthenticationEvent>((event, emit) async {
      await event.map(started: (e) async {
        emit(const AuthenticationState.loading());
        try {
          LoginResponse loginResponse =
              await authenticationRepository.login(e.request);

          authenticationRepository.dio.options.headers["Authorization"] =
              "Bearer ${loginResponse.token}";
          GetStorage storage = GetStorage();
          await storage.write("token", loginResponse.token);
          await storage.write("user", loginResponse.user.toJson().toString());
          emit(AuthenticationState.authenticated(loginResponse.user));
        } on DioException catch (e) {
          log(e.toString(), error: e);
          emit(const AuthenticationState.unauthenticated());
        }
      });
      // TODO: implement event handler
    });
  }
}
