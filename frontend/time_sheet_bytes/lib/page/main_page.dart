import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:localstorage/localstorage.dart';
import 'package:r_nav_n_sheet/r_nav_n_sheet.dart';
import 'package:time_range/time_range.dart';
import 'package:time_sheet_bytes/page/add_task_page.dart';
import 'package:time_sheet_bytes/page/profile_page.dart';
import 'package:time_sheet_bytes/services/first_look/first_loock_cubit.dart';

import '../component/task_sheet.dart';
import '../component/text_field_comp.dart';
import '../model/user.dart';
import '../services/task/task_cubit.dart';

class MainPageView extends StatefulWidget {
  const MainPageView({super.key});

  @override
  State<MainPageView> createState() => _MainPageViewState();
}

class _MainPageViewState extends State<MainPageView> {
  List<Widget> pages = [];
  List<Widget> sheet = [TaskSheet(), ProfileSheet()];
  Widget currentPage = Container();
  Widget currentSheet = Container();
  @override
  void initState() {
    super.initState();
    pages = [
      AddTaskPageView(
        sheet: sheet[0],
      ),
      ProfilePageView()
    ];
    currentPage = pages[0];
    currentSheet = sheet[0];
  }

  navigateTo(int index) {
    setState(() {
      currentPage = pages[index];
      currentSheet = sheet[index];
      print(currentSheet.runtimeType);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      bottomNavigationBar: RNavNSheet(
        backgroundColor: Colors.white,
        sheetOpenIconColor: Colors.white,
        sheetOpenIconBoxColor: Colors.black,
        sheet: currentSheet,
        onTap: (index) => navigateTo(index),
        onSheetToggle: (value) {
          if (value == true) {
            print("ok");
            context.read<TaskCubit>().emitAdded();
          }
        },
        initialSelectedIndex: 0,
        items: const [
          RNavItem(
            icon: Icons.home_outlined,
            activeIcon: Icons.home,
            label: "Tasks",
          ),
          RNavItem(
            icon: Icons.search_outlined,
            activeIcon: Icons.verified_user,
            label: "Profile",
          ),
        ],
      ),
      body: SizedBox(height: double.infinity, child: currentPage),
    );
  }
}

class ProfileSheet extends StatelessWidget {
  ProfileSheet({super.key}) {
    print(json.decode(localStorage.getItem('user')!));
    user = userFromJson(localStorage.getItem('user')!);
    nameController.text = user!.name;
    emailController.text = user!.emailTo;
  }

  User? user;
  TextEditingController nameController = TextEditingController();
  TextEditingController emailController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return BottomSheet(
      backgroundColor: Colors.white,
      onClosing: () {},
      builder: (context) {
        return Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            children: [
              Row(
                children: [
                  Text(
                    "Edit Profile",
                    style: GoogleFonts.poppins(
                        color: Colors.black,
                        fontSize: 20,
                        fontWeight: FontWeight.w600),
                  ),
                ],
              ),
              const Divider(),
              const SizedBox(
                height: 20,
              ),
              TextFieldCompView(
                controller: nameController,
                hint: "Name",
              ),
              const SizedBox(
                height: 20,
              ),
              TextFieldCompView(
                controller: emailController,
                hint: "Email to",
              ),
              const SizedBox(
                height: 20,
              ),
              const Spacer(
                flex: 1,
              ),
              SizedBox(
                width: double.infinity,
                height: 60,
                child: MaterialButton(
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15)),
                  onPressed: () {
                    if (nameController.text.isEmpty ||
                        emailController.text.isEmpty) {
                      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(
                        content: Text("Please fill all the fields"),
                      ));
                    } else {
                      user!.name = nameController.text;
                      user!.emailTo = emailController.text;
                      localStorage.setItem('user', userToJson(user!));
                      context.read<FirstLoockCubit>().succes();
                      Navigator.pop(context);
                    }
                  },
                  color: Colors.black,
                  child: Text(
                    "Save",
                    style: GoogleFonts.poppins(
                        fontWeight: FontWeight.w600, color: Colors.white),
                  ),
                ),
              ),
              const Spacer(
                flex: 1,
              )
            ],
          ),
        );
      },
    );
  }
}
