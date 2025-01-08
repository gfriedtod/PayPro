import 'package:bloc/bloc.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:pay_pro_app/model/document.dart';

import '../../repository/user_repository.dart';

part 'user_event.dart';
part 'user_state.dart';
part 'user_bloc.freezed.dart';

class UserBloc extends Bloc<UserEvent, UserState> {
  final UserRepository repository;
  UserBloc({required this.repository}) : super(const UserState.initial()) {
    on<UserEvent>((event, emit) async {
     await event.maybeMap(orElse: () => null, started: (value) {
        emit(const UserState.loading());
      },
          fetchDocument: (value)  async {
          emit(const UserState.loading());
          try {
        final doc = await   repository
                .fetchDocument(value.userId);

               emit(UserState.loaded(doc));
          } catch (e) {
            print(e);
            emit(const UserState.error());
          }
      });
      // TODO: implement event handler
    });
  }
}
