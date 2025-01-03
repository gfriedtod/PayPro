import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:localstorage/localstorage.dart';
import 'package:lucide_icons/lucide_icons.dart';
import 'package:time_sheet_bytes/model/user.dart';
import 'package:time_sheet_bytes/services/first_look/first_loock_cubit.dart';
import 'package:time_sheet_bytes/services/first_look/first_loock_cubit.dart';

import '../component/presentation_comp.dart';

class ProfilePageView extends StatelessWidget {
  ProfilePageView({super.key});

  User user = User(name: 'Rahul Kumar', emailTo: 'bLZkM@example.com');

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<FirstLoockCubit, FirstLoockState>(
      builder: (context, state) {
        user = userFromJson(localStorage.getItem('user')!);
        return Scaffold(
          backgroundColor: Colors.white,
          appBar: AppBar(
            backgroundColor: Colors.white,
            leadingWidth: double.infinity,
            leading: SizedBox(
              height: 100,
              child: Column(
                mainAxisAlignment: MainAxisAlignment.end,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Text(
                      "Profile",
                      style: GoogleFonts.poppins(
                          fontWeight: FontWeight.w600, fontSize: 25),
                    ),
                  ),
                ],
              ),
            ),
          ),
          body: SingleChildScrollView(
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: Center(
                child: Column(
                  children: [
                    PresentationView(
                      title: 'Name',
                      iconData: Icons.label,
                      value: user.name,
                    ),
                    PresentationView(
                      title: 'Email to',
                      iconData: LucideIcons.mail,
                      value: user.emailTo,
                    ),
                  ],
                ),
              ),
            ),
          ),
        );
      },
    );
  }
}
