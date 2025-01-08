/// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

import 'dart:convert';

Document documentFromJson(String str) => Document.fromJson(json.decode(str));

String documentToJson(Document data) => json.encode(data.toJson());

class Document {
    Document({
        required this.name,
        required this.link,
        required this.id,
        required this.dateFile,
    });

    String name;
    String link;
    String id;
    DateTime dateFile;

    factory Document.fromJson(Map<dynamic, dynamic> json) => Document(
        name: json["name"],
        link: json["link"],
        id: json["id"],
        dateFile: DateTime.parse(json["dateFile"]),
    );

    Map<dynamic, dynamic> toJson() => {
        "name": name,
        "link": link,
        "id": id,
        "dateFile": "${dateFile.year.toString().padLeft(4, '0')}-${dateFile.month.toString().padLeft(2, '0')}-${dateFile.day.toString().padLeft(2, '0')}",
    };
}
