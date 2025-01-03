import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:localstorage/localstorage.dart';
import 'package:time_sheet_bytes/page/add_task_page.dart';
import 'package:time_sheet_bytes/page/main_page.dart';
import 'package:time_sheet_bytes/page/personal_information_page.dart';
import 'package:time_sheet_bytes/page/profile_page.dart';
import 'package:time_sheet_bytes/services/first_look/first_loock_cubit.dart';
import 'package:time_sheet_bytes/services/task/task_cubit.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await initLocalStorage(); // Initialize GetStorage
  SystemChrome.setPreferredOrientations(
      [DeviceOrientation.portraitUp, DeviceOrientation.portraitDown]);


  runApp( MyApp());
}

class MyApp extends StatelessWidget {
   MyApp({super.key});

  final Dio dio = Dio(
    BaseOptions(
      baseUrl: 'https://sheetdb.io/api/v1/',
    ),
  );

  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
      providers: [
        BlocProvider(
          create: (context) =>
          FirstLoockCubit()..init()
            ,
        ),
        BlocProvider(
          create: (context) => TaskCubit(dio),
        ),
      ],
      child: MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          useMaterial3: true,
        ),
        home: BlocConsumer<FirstLoockCubit, FirstLoockState>(
          listener: (context, state) {
            state.maybeMap(
              orElse: () {},
              initial: (_) {
              },
            );
          },
          builder: (context, state) {
            return state.maybeMap(
              orElse: () =>
              const Scaffold(
                backgroundColor: Colors.black,
                body: Center(
                  child: CircularProgressIndicator(
                    color: Colors.black,
                    strokeWidth: 6,
                  ),
                ),
              ),
              firsTime: (_) =>  PersonalInformationPageView(),
              user: (_) => const MyHomePage(title: ''),
            );

            const MyHomePage(title: 'Flutter Demo Home Page');
          },
        ),
      ),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return const MainPageView();
  }
}
