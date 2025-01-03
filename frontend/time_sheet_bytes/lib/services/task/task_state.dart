part of 'task_cubit.dart';

@freezed
class TaskState with _$TaskState {
  const factory TaskState.initial(List<Task> tasks) = _Initial;
  const factory TaskState.loading() = _Loading;
  const factory TaskState.loaded(List<Task> tasks) = _Loaded;
  const factory TaskState.error(String message ,{List<Task>? tasks}) = _Error;
  const factory TaskState.empty() = _Empty;
  const factory TaskState.added(List<Task> tasks) = _Added;
  const factory TaskState.initUpdate(List<Task> tasks) = _InitUpdate;
}
