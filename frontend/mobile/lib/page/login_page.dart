import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:pay_pro_app/page/home_page.dart';
import 'package:pay_pro_app/services/authentication/authentication_bloc.dart';

import '../model/login_request.dart';

class LoginPageView extends StatelessWidget {
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  LoginPageView({super.key});

  @override
  Widget build(BuildContext context) {
    var size = MediaQuery.of(context).size;
    return Scaffold(
      backgroundColor: Colors.white,
      body: Center(
        child: SingleChildScrollView(
          child: SizedBox(
            width: size.width * 0.9,
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  SizedBox(
                    height: 200,
                    width: 200,
                    child: Image.asset(
                      "assets/images/logo.png",
                    ),
                  ),
                  Text(
                    'Bienvenue sur notre plateforme',
                    style: GoogleFonts.poppins(
                        fontWeight: FontWeight.w700, fontSize: 20),
                    textAlign: TextAlign.center,
                  ),
                  const SizedBox(
                    height: 30,
                  ),
                  TextFormField(
                    controller: emailController,
                    cursorColor: Colors.black,
                    decoration: const InputDecoration(
                      focusedBorder: OutlineInputBorder(
                        borderSide: BorderSide(color: Colors.black, width: 3),
                      ),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.all(Radius.circular(10))),
                      labelText: "Email",
                      labelStyle: TextStyle(color: Colors.black),
                    ),
                  ),
                  const SizedBox(
                    height: 10,
                  ),
                  TextFormField(
                    controller: passwordController,
                    cursorColor: Colors.black,
                    decoration: const InputDecoration(
                      focusedBorder: OutlineInputBorder(
                        borderSide: BorderSide(color: Colors.black, width: 3),
                      ),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.all(Radius.circular(10))),
                      labelText: "Password",
                      labelStyle: TextStyle(color: Colors.black),
                    ),
                  ),
                  const SizedBox(
                    height: 50,
                  ),
                  BlocConsumer<AuthenticationBloc, AuthenticationState>(
                    listener: (context, state) {
                      print(state);
                      state.maybeWhen(orElse: (){},
                        unauthenticated: (){
                          ScaffoldMessenger.of(context).showSnackBar(
                              const SnackBar(
                                  content: Text("Connexion échouée")));
                          print("Connexion échouée");
                        },
                        authenticated: (value){
                          ScaffoldMessenger.of(context).showSnackBar(
                              const SnackBar(
                                  content: Text("Connexion reussie")));
                          print("Connexion reussie");
                          Navigator.push(context, MaterialPageRoute(builder: (context)=> HomePageView(user: value)));
                        }
                      );
                    },
                    builder: (context, state) {
                      return state.maybeWhen(
                          loading: () =>
                              Button(size: size, state: true, onPressed: () {}),
                          orElse: () => Button(
                                size: size,
                                state: false,
                                onPressed: () {
                                  context.read<AuthenticationBloc>().add(
                                      AuthenticationEvent.started(LoginRequest(
                                          email: emailController.text.replaceAll(' ', ''),
                                          password: passwordController.text)));
                                },
                              ));
                    },
                  ),
                  const SizedBox(
                    height: 30,
                  ),
                  Text(
                    'Veuillez vous connecter pour pouvoir continuer',
                    style: GoogleFonts.poppins(
                        fontWeight: FontWeight.w300, fontSize: 12),
                    textAlign: TextAlign.center,
                  )
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}

class Button extends StatelessWidget {
  Button(
      {super.key,
      required this.size,
      required this.state,
      required this.onPressed});

  final Size size;
  final bool state;
  VoidCallback onPressed;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
        width: size.width * 0.9,
        height: 48,
        child: MaterialButton(
            shape:
                RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
            color: Colors.black,
            onPressed: onPressed,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                if (state)
                  const SizedBox(
                    width: 20,
                    height: 20,
                    child: CircularProgressIndicator(
                      color: Colors.white,
                    ),
                  ),
                const SizedBox(
                  width: 10,
                ),
                Text(
                  "Connexion",
                  style: GoogleFonts.poppins(
                      fontWeight: FontWeight.w500,
                      fontSize: 15,
                      color: Colors.white),
                ),
              ],
            )));
  }
}
