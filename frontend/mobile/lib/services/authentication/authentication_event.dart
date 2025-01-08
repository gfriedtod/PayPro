part of 'authentication_bloc.dart';

@freezed
class AuthenticationEvent with _$AuthenticationEvent {
  const factory AuthenticationEvent.started(LoginRequest request) = _Started;

  const factory AuthenticationEvent.logout() = _Logout;
}
