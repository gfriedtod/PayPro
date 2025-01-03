import 'dart:convert';
import 'dart:io';

import 'package:bloc/bloc.dart';
import 'package:date_format/date_format.dart';
import 'package:dio/dio.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:localstorage/localstorage.dart';
import 'package:path_provider/path_provider.dart';
import 'package:time_sheet_bytes/model/user.dart';

import '../../model/task.dart';

part 'task_state.dart';
part 'task_cubit.freezed.dart';

class TaskCubit extends Cubit<TaskState> {
  List<Task> tasks = [];
  final Dio dio;

  TaskCubit(this.dio) : super(const TaskState.initial([]));

  void addTask(Task task) {
    emit(const TaskState.loading());
    tasks.add(task);
    emit(TaskState.added(tasks));
  }

  void emitAdded() {
    emit(TaskState.added(tasks));
  }

  void removeTask(Task task) {
    tasks.remove(task);
    emit(TaskState.added(tasks));
  }

  void updateTask(Task task, int index) {
    emit(const TaskState.loading());
    tasks[index] = task;
    emit(TaskState.added(tasks));
  }

  void initUpdate() {
    emit(const TaskState.loading());
    emit(TaskState.initUpdate(tasks));
  }

  sendEmail() async {
    try {
      emit(const TaskState.loading());
      // Replace with your actual Resend API base URL
      const baseUrl = 'https://api.resend.com';
      User user = userFromJson(localStorage.getItem('user')!);

      // Replace with your actual Resend API key
      const apiKey = 're_iauGTw95_NfCpq8LksZBsGJCNK3jevcpi';
      File file =
          await convertJsonToCsv(tasks.map((task) => task.toJson()).toList());
      print((file.readAsBytesSync()));
      final data = {
        "from": "onboarding@resend.dev",
        "to": user.emailTo,
        "subject": "Daily Task of ${user.name}",
        "attachments": [
          {
            "content": base64Encode(file.readAsBytesSync()),
            "filename": file.path.split('/').last,
          },
        ],
        "html":
            "<p> There is daily task of  <strong>${user.name}</strong>!</p>",
      };

      final options = BaseOptions(
        baseUrl: baseUrl,
        headers: {
          'Authorization': 'Bearer $apiKey',
          'Content-Type': 'application/json',
        },
      );

      dio.options = options;
      final response = await dio.post('/emails', data: jsonEncode(data));
      tasks = [];
      emit(TaskState.initial(tasks));
      return response;
    } catch (e) {
      // Handle error (e.g., print error message, throw exception)
      print('Error sending email: ${e}');
      // Or throw a specific exception you define
      emit(TaskState.error('Error sending email: ${e.toString()}',
          tasks: tasks));
    }
  }

  sendMailjetEmail() async {
    try {
      emit(const TaskState.loading());

      // Basic authentication credentials
      User user = userFromJson(localStorage.getItem('user')!);

      String credentials = base64Encode(utf8.encode(
          "afa8a4a91cdd4720022b00ba9fe28547:c83ae2631b89e4edef3013b6a4340cc1"));
      File file =
          await convertJsonToCsv(tasks.map((task) => task.toJson()).toList());


      final messageData = {
        "Messages": [
          {
            "From": {
              "Email": "gemini.gfried@gmail.com",
              "Name": user.name,
            },
            "To": [
              {
                "Email": user.emailTo,
                "Name": user.name
              }
            ],
            "Subject": "Daily Task of ${user.name}",
            "TextPart": "Daily Task of ${user.name}",
            "HTMLPart":
                "<p> There is daily task of  <strong>${user.name}</strong>!</p>",
            "Attachments": [
              {
                "ContentType": "text/plain",
                "Filename": file.path.split('/').last,
                "Base64Content": base64Encode(file.readAsBytesSync()),
              }
            ]
          }
        ]
      };

      dio.options = BaseOptions(
        baseUrl: "https://api.mailjet.com/v3.1",
        headers: {
          'Authorization': 'Basic $credentials',
          'Content-Type': 'application/json',
        },
      );

      try {
        final response = await dio.post(
          "https://api.mailjet.com/v3.1/send",
          data: messageData,
        );

        tasks= [];

       emit( TaskState.added(tasks));
      } on DioException catch (e) {
        // Handle error (e.g., print error message)
        print('Error sending email: ${e}');
        // Or throw a specific exception you define
        emit(
            TaskState.error('Error sending email: ${e.message}', tasks: tasks));
      }
    } catch (e) {
      print(e);
      emit(TaskState.error('Error sending email: ${e.toString()}',
          tasks: tasks));
    }
  }

  Future<File> convertJsonToCsv(List<Map<dynamic, dynamic>> jsonData,
      {String delimiter = ",", String encloseWith = '"'}) async {
    try {
      if (jsonData.isEmpty) {
        print("JSON data is empty. No CSV file will be created.");
        throw Exception('Tasks list is empty.');
      }
      var headers = jsonData[0].keys.join(",");
      var rows = List.generate(
        jsonData.length,
        (index) => jsonData[index].values.join(","),
      );

      User user = userFromJson(localStorage.getItem('user')!);
      try {
        Directory appDocDirectory = await getApplicationDocumentsDirectory();

        Directory dir = await new Directory('${appDocDirectory.path}/tasks')
            .create(recursive: true);
        File file = await File(
                '${dir.path}/${user.name}-tasks-${formatDate(DateTime.now(), [
              yyyy,
              '-',
              mm,
              '-',
              dd
            ])}.csv')
            .create();

        // Récupérer les clés (noms des colonnes) depuis le premier élément JSON.
        final headers = jsonData.first.keys.toList();
        // Écrire l'en-tête CSV.
        // Écrire les données CSV.
        String csvData = '$headers\n${rows.join("\n")}';

        await file.writeAsString(csvData);
        print('CSV file created successfully at:');
        return file;
      } catch (e) {
        print('Error creating CSV file: $e');
        throw Exception('Error creating CSV file: $e');
      }
    } catch (e) {
      print('Error creating CSV file: $e');
      throw Exception(e);
    }
  }
}
