import 'dart:convert';
import 'dart:io';

import 'package:dart_jsonwebtoken/dart_jsonwebtoken.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:get_storage/get_storage.dart';
import 'package:jwt_decoder/jwt_decoder.dart';
import 'package:localstorage/localstorage.dart';
import 'package:pay_pro_app/model/login_response.dart';
import 'package:pay_pro_app/page/home_page.dart';
import 'package:pay_pro_app/page/login_page.dart';
import 'package:pay_pro_app/repository/authentication_repository.dart';
import 'package:pay_pro_app/repository/user_repository.dart';
import 'package:pay_pro_app/services/authentication/authentication_bloc.dart';
import 'package:pay_pro_app/services/user/user_bloc.dart';
import 'package:shared_preferences/shared_preferences.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await initLocalStorage();; // Initialize GetStorage
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final Dio dio = Dio(BaseOptions(baseUrl: 'http://10.0.2.2:8080/modulith/v1'));
  late Widget _initialScreenFuture;

  @override
  void initState() {
    super.initState();
    _initialScreenFuture = _determineInitialScreen();
  }

  Widget _determineInitialScreen()  {

    try {
      String? token = localStorage.getItem('token');
      String? userJson = localStorage.getItem('user');

      if (token == null || userJson == null) {
        print('Aucun token trouvé');
        return LoginPageView();
      }

      // Vérifier l'expiration du token
      if (JwtDecoder.isExpired(token)) {
        localStorage.clear();
        return LoginPageView();
      }

      // Configurer l'en-tête du token pour Dio
      dio.options.headers["Authorization"] = "Bearer $token";

      // Si l'utilisateur est stocké, aller à la page d'accueil
      User user = User.fromJson(json.decode(userJson));
      return HomePageView(user: user);

    } catch (e) {
      print('Une erreur s’est produite lors du traitement du token: $e');

      return LoginPageView();
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
          home: _initialScreenFuture,
        ),
      ),
    );
  }
}