import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:localstorage/localstorage.dart';
import 'package:time_sheet_bytes/component/text_field_comp.dart';
import 'package:time_sheet_bytes/services/first_look/first_loock_cubit.dart';

import '../model/user.dart';

class PersonalInformationPageView extends StatelessWidget {
  PersonalInformationPageView({super.key});
  final TextEditingController nameController = TextEditingController();
  final TextEditingController emailToController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        leadingWidth: double.infinity,
        backgroundColor: Colors.white,
        leading: const SizedBox(
          height: 100,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.end,
            children: [],
          ),
        ),
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            children: [
              const SizedBox(
                height: 20,
              ),
              Text(
                "Welcome to Time Sheet Bytes the best way to manage your tasks",
                style: GoogleFonts.poppins(
                  fontSize: 25,
                  fontWeight: FontWeight.w700,
                ),
                textAlign: TextAlign.center,
              ),
              const SizedBox(
                height: 8,
              ),
              const SizedBox(
                height: 20,
              ),
              TextFieldCompView(
                hint: "Name",
                controller: nameController,
              ),
              const SizedBox(
                height: 20,
              ),
              TextFieldCompView(
                hint: "Email To",
                controller: emailToController,
              ),
              const SizedBox(
                height: 20,
              ),
              Text(
                "Let us know your personal information",
                style: GoogleFonts.poppins(
                    fontSize: 15,
                    fontWeight: FontWeight.w600,
                    color: Colors.grey),
                textAlign: TextAlign.center,
              ),
              const Spacer(),
              SizedBox(
                width: double.infinity,
                height: 60,
                child: MaterialButton(
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15)),
                  onPressed: () {
                    if (nameController.text.isEmpty ||
                        emailToController.text.isEmpty) {
                      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(
                        content: Text("Please fill all the fields"),
                      ));
                    } else {

                      localStorage.setItem(
                          'user',
                           userToJson(User(
                              name: nameController.text,
                              emailTo: emailToController.text)));

                      context.read<FirstLoockCubit>().succes();                  }
                  },
                  color: Colors.black,
                  child: Text(
                    "Save",
                    style: GoogleFonts.poppins(
                        fontWeight: FontWeight.w600, color: Colors.white),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
