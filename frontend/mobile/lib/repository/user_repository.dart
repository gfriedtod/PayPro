import 'dart:developer';

import 'package:dio/dio.dart';
import 'package:pay_pro_app/model/document.dart';
import 'package:pay_pro_app/model/login_response.dart';

class UserRepository {
  final Dio dio;
  UserRepository({required this.dio});

  fetchDocument(String userId) async {
    try {
      Response res =
          await dio.get("/file/user/$userId");
      if (res.statusCode == 200) {
        return (res.data as List).map((doc) => Document.fromJson(doc))?.toList() ?? [];
      }
      throw Exception("Un probleme est survenu");
    } catch (e) {
      log(e.toString());
      throw Exception(e);
    }
  }
}
