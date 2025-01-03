part of 'first_loock_cubit.dart';

@freezed
class FirstLoockState with _$FirstLoockState {
  const factory FirstLoockState.initial() = _Initial;
  const factory FirstLoockState.loading() = _Loading;
  const factory FirstLoockState.firsTime() = _FirstTime;
  const factory FirstLoockState.user() = _User;
}
