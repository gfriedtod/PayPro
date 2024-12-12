/// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

import 'dart:convert';

LoginRequest loginRequestFromJson(String str) => LoginRequest.fromJson(json.decode(str));

String loginRequestToJson(LoginRequest data) => json.encode(data.toJson());

class LoginRequest {
    LoginRequest({
        required this.password,
        required this.email,
    });

    String password;
    String email;

    factory LoginRequest.fromJson(Map<dynamic, dynamic> json) => LoginRequest(
        password: json["password"],
        email: json["email"],
    );

    Map<dynamic, dynamic> toJson() => {
        "password": password,
        "email": email,
    };
}
