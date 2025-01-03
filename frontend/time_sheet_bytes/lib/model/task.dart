/// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

import 'dart:convert';

Task taskFromJson(String str) => Task.fromJson(json.decode(str));

String taskToJson(Task data) => json.encode(data.toJson());

class Task {
    Task({
        required this.date,
        required this.start,
        required this.name,
        required this.description,
        required this.end,
    });

    String date;
    String start;
    String end;
    String name;
    String description;

    factory Task.fromJson(Map<dynamic, dynamic> json) => Task(
        date: json["date"],
        start: json["start"],
        end: json["end"],

        name: json["name"],
        description: json["description"],
    );

    Map<dynamic, dynamic> toJson() => {
        "date": date,
        "start": start,
        "end": end,
        "name": name,
        "description": description,
    };
}
