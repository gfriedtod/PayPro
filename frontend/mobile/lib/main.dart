import 'dart:convert';
import 'dart:io';

import 'package:dart_jsonwebtoken/dart_jsonwebtoken.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:get_storage/get_storage.dart';
import 'package:jwt_decoder/jwt_decoder.dart';
import 'package:pay_pro_app/model/login_response.dart';
import 'package:pay_pro_app/page/home_page.dart';
import 'package:pay_pro_app/page/login_page.dart';
import 'package:pay_pro_app/repository/authentication_repository.dart';
import 'package:pay_pro_app/repository/user_repository.dart';
import 'package:pay_pro_app/services/authentication/authentication_bloc.dart';
import 'package:pay_pro_app/services/user/user_bloc.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  GetStorage.init(); // Initialize GetStorage
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final Dio dio = Dio(BaseOptions(baseUrl: 'http://192.168.137.1:8080/modulith/v1'));
  late Future<Widget> _initialScreenFuture;

  @override
  void initState() {
    super.initState();
    _initialScreenFuture = _determineInitialScreen();
  }

  Future<Widget> _determineInitialScreen() async {
    final storage = GetStorage();

    try {
      String? token = storage.read('token');
      String? userJson = storage.read('user');

      if (token == null) {
        print('Aucun token trouvé');
        return LoginPageView();
      }

      // Vérifier l'expiration du token
      if (isTokenExpired(token)) {
        storage.remove('token');
        return LoginPageView();
      }

      // Configurer l'en-tête du token pour Dio
      dio.options.headers["Authorization"] = "Bearer $token";

      // Si l'utilisateur est stocké, aller à la page d'accueil
      if (userJson != null) {
        var user = User.fromJson(json.decode(userJson));
        return HomePageView(user: user);
      }

      return LoginPageView();
    } catch (e) {
      print("Erreur d'authentification : $e");
      return LoginPageView();
    }
  }

  bool isTokenExpired(String token) {
    try {
      Map<String, dynamic> decodedToken = JwtDecoder.decode(token);
      return decodedToken['exp'] < DateTime.now().millisecondsSinceEpoch / 1000;
    } catch (e) {
      return true;
    }
  }

  @override
  Widget build(BuildContext context) {
    return MultiRepositoryProvider(
      providers: [
        RepositoryProvider(
          create: (context) => AuthenticationRepository(dio: dio),
        ),
        RepositoryProvider(create: (context) => UserRepository(dio: dio)),
      ],
      child: MultiBlocProvider(
        providers: [
          BlocProvider(
            create: (context) => AuthenticationBloc(
                authenticationRepository: context.read<AuthenticationRepository>()
            ),
          ),
          BlocProvider(
              create: (context) => UserBloc(repository: context.read<UserRepository>())
          ),
        ],
        child: MaterialApp(
          title: 'Flutter Demo',
          theme: ThemeData(
            colorScheme: ColorScheme.fromSeed(seedColor: Colors.black),
            useMaterial3: true,
          ),
          home: FutureBuilder<Widget>(
            future: _initialScreenFuture,
            builder: (context, snapshot) {
              switch (snapshot.connectionState) {
                case ConnectionState.waiting:
                  return const Scaffold(
                    backgroundColor: Colors.white,
                    body: Center(
                      child: CircularProgressIndicator(
                        color: Colors.black,
                        strokeWidth: 8,
                      ),
                    ),
                  );
                case ConnectionState.active:
                case ConnectionState.done:
                  if (snapshot.hasError) {
                    print("Erreur: ${snapshot.error}");
                    return LoginPageView();
                  }
                  return snapshot.data ?? LoginPageView();
                case ConnectionState.none:
                  return LoginPageView();
              }
            },
          ),
        ),
      ),
    );
  }
}