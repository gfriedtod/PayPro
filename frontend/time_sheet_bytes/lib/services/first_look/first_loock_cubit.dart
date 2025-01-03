import 'package:bloc/bloc.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:localstorage/localstorage.dart';

part 'first_loock_state.dart';
part 'first_loock_cubit.freezed.dart';

class FirstLoockCubit extends Cubit<FirstLoockState> {
  FirstLoockCubit() : super(const FirstLoockState.initial());

  void init() {
    emit(const FirstLoockState.loading());
    final isFirstLook = localStorage.getItem('user');
    if (isFirstLook == null) {
      emit( const FirstLoockState.firsTime());
    } else {
      emit(const FirstLoockState.user());
    }
  }
  void succes(){
    emit(const FirstLoockState.loading());
    emit(const FirstLoockState.user());
  }
}
