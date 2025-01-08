import 'package:dio/dio.dart';
import 'package:pay_pro_app/model/login_request.dart';
import 'package:pay_pro_app/model/login_response.dart';

class AuthenticationRepository {
  final Dio dio;

  AuthenticationRepository({required this.dio});

  login(LoginRequest loginRequest) async {
    try {
      Response res = await dio.post('/auth/login/user', data: loginRequest.toJson());
      if (res.statusCode == 200) {
        LoginResponse loginResponse = LoginResponse.fromJson(res.data);
        return loginResponse;
      } else {
        throw DioException(requestOptions: res.requestOptions);
      }
    } on DioException catch (e) {
      rethrow;
    }
  }
}
