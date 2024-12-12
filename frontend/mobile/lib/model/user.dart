/// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

import 'dart:convert';

User userFromJson(String str) => User.fromJson(json.decode(str));

String userToJson(User data) => json.encode(data.toJson());

class User {
    User({
        required this.address,
        required this.gender,
        required this.displayName,
        required this.rule,
        required this.password,
        required this.phone,
        required this.name,
        required this.id,
        required this.department,
        required this.dateBirth,
        required this.email,
        required this.cni,
    });

    String address;
    String gender;
    String displayName;
    String rule;
    String password;
    String phone;
    String name;
    String id;
    Department department;
    DateTime dateBirth;
    String email;
    String cni;

    factory User.fromJson(Map<dynamic, dynamic> json) => User(
        address: json["address"],
        gender: json["gender"],
        displayName: json["displayName"],
        rule: json["rule"],
        password: json["password"],
        phone: json["phone"],
        name: json["name"],
        id: json["id"],
        department: Department.fromJson(json["department"]),
        dateBirth: DateTime.parse(json["dateBirth"]),
        email: json["email"],
        cni: json["cni"],
    );

    Map<dynamic, dynamic> toJson() => {
        "address": address,
        "gender": gender,
        "displayName": displayName,
        "rule": rule,
        "password": password,
        "phone": phone,
        "name": name,
        "id": id,
        "department": department.toJson(),
        "dateBirth": "${dateBirth.year.toString().padLeft(4, '0')}-${dateBirth.month.toString().padLeft(2, '0')}-${dateBirth.day.toString().padLeft(2, '0')}",
        "email": email,
        "cni": cni,
    };
}

class Department {
    Department({
        required this.createdAt,
        required this.name,
        required this.organisation,
        required this.id,
    });

    DateTime createdAt;
    String name;
    Organisation organisation;
    String id;

    factory Department.fromJson(Map<dynamic, dynamic> json) => Department(
        createdAt: DateTime.parse(json["createdAt"]),
        name: json["name"],
        organisation: Organisation.fromJson(json["organisation"]),
        id: json["id"],
    );

    Map<dynamic, dynamic> toJson() => {
        "createdAt": createdAt.toIso8601String(),
        "name": name,
        "organisation": organisation.toJson(),
        "id": id,
    };
}

class Organisation {
    Organisation({
        required this.name,
        required this.id,
    });

    String name;
    String id;

    factory Organisation.fromJson(Map<dynamic, dynamic> json) => Organisation(
        name: json["name"],
        id: json["id"],
    );

    Map<dynamic, dynamic> toJson() => {
        "name": name,
        "id": id,
    };
}
