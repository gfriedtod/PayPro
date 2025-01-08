import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_snake_navigationbar/flutter_snake_navigationbar.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:pay_pro_app/model/login_response.dart';
import 'package:pay_pro_app/page/login_page.dart';
import 'package:pay_pro_app/page/pdf_page.dart';

import '../services/authentication/authentication_bloc.dart';
import '../services/user/user_bloc.dart';

class HomePageView extends StatelessWidget {
  const HomePageView({super.key, required this.user});

  final User user;

  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    double height = MediaQuery.of(context).size.height;
    return Scaffold(
      backgroundColor: Colors.white,
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () {
          context.read<AuthenticationBloc>().add(AuthenticationEvent.logout());
          Navigator.pushReplacement(context, MaterialPageRoute(
            builder: (context) =>  LoginPageView(),
          ));

        },
        backgroundColor: Colors.black,
        label:  Text("Logout",style: GoogleFonts.poppins(color: Colors.white),),
        icon: const Icon(
          Icons.logout,
          color: Colors.white,
        ),
      ),
      appBar: AppBar(
        automaticallyImplyLeading: false,
        forceMaterialTransparency: true,
        title: SizedBox(
          //  height: 50,
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  "My PaySlip",
                  style: GoogleFonts.inter(
                      fontSize: 30, fontWeight: FontWeight.bold),
                ),
                Text.rich(TextSpan(
                    text: "Welcome ",
                    style: GoogleFonts.inter(fontSize: 15),
                    children: [
                      TextSpan(
                          text: "${user.name} ${user.displayName}",
                          style: GoogleFonts.inter(
                              fontSize: 15, fontWeight: FontWeight.bold))
                    ]))
              ],
            ),
          ),
        ),
      ),
      body: BlocBuilder<UserBloc, UserState>(
        builder: (context, state) {
          return state.maybeWhen(
              orElse: () => RefreshIndicator(
                  onRefresh: () async => context
                      .read<UserBloc>()
                      .add(UserEvent.fetchDocument(user.id)),
                  child: PageView(width: width, user: user, height: height)),
              loading: () => const Center(
                      child: CircularProgressIndicator(
                    color: Colors.black,
                    strokeWidth: 8,
                  )),
              initial: () {
                context.read<UserBloc>().add(UserEvent.fetchDocument(user.id));
                return RefreshIndicator(
                    onRefresh: () async => context
                        .read<UserBloc>()
                        .add(UserEvent.fetchDocument(user.id)),
                    child: PageView(width: width, user: user, height: height));
              });
        },
      ),
    );
  }
}

class PageView extends StatelessWidget {
  const PageView({
    super.key,
    required this.width,
    required this.user,
    required this.height,
  });

  final double width;
  final User user;
  final double height;

  @override
  Widget build(BuildContext context) {
    return Center(
        child: Padding(
      padding: const EdgeInsets.fromLTRB(10.0, 30.0, 10.0, 10.0),
      child: SizedBox(
        width: width * 0.9,
        child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                "Personals information",
                style: GoogleFonts.inter(
                    fontSize: 20, fontWeight: FontWeight.bold),
              ),
              const SizedBox(
                height: 10,
              ),
              Row(
                children: [
                  Container(
                    height: 120,
                    width: 120,
                    decoration: BoxDecoration(
                      color: Colors.red,
                      borderRadius: BorderRadius.circular(10),
                      image: const DecorationImage(
                          image: NetworkImage(
                              "https://afriksportsmagazine.com/wp-content/uploads/2024/02/C3N09690.webp"),
                          fit: BoxFit.cover),
                    ),
                  ),
                  const SizedBox(
                    width: 20,
                  ),
                  SizedBox(
                    width: width * 0.4,
                    child: Row(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    "Role",
                                    style: GoogleFonts.inter(
                                        fontSize: 15,
                                        fontWeight: FontWeight.w500,
                                        color: Colors.grey),
                                  ),
                                  Text(
                                    user.rule,
                                    style: GoogleFonts.inter(
                                        fontSize: 15,
                                        fontWeight: FontWeight.bold),
                                  ),
                                ],
                              ),
                              Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    "Gender",
                                    style: GoogleFonts.inter(
                                        fontSize: 15,
                                        fontWeight: FontWeight.w500,
                                        color: Colors.grey),
                                  ),
                                  Text(
                                    user.gender,
                                    style: GoogleFonts.inter(
                                        fontSize: 15,
                                        fontWeight: FontWeight.bold),
                                  ),
                                ],
                              ),
                              Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    "Departement",
                                    style: GoogleFonts.inter(
                                        fontSize: 15,
                                        fontWeight: FontWeight.w500,
                                        color: Colors.grey),
                                  ),
                                  Text(
                                    user.department.name,
                                    style: GoogleFonts.inter(
                                        fontSize: 15,
                                        fontWeight: FontWeight.bold),
                                  ),
                                ],
                              )
                            ]),
                        const SizedBox(
                          width: 10,
                        ),
                      ],
                    ),
                  )
                ],
              ),
              const SizedBox(
                height: 20,
              ),
              Text(
                "PaySlip",
                style: GoogleFonts.inter(
                    fontSize: 20, fontWeight: FontWeight.bold),
              ),
              const SizedBox(
                height: 10,
              ),
              SizedBox(
                height: height * 0.4,
                child: BlocConsumer<UserBloc, UserState>(
                  listener: (context, state) {
                    state.maybeWhen(
                        orElse: () {},
                        // initial: () {
                        //   context
                        //       .read<UserBloc>()
                        //       .add(UserEvent.fetchDocument(user.id));
                        // },
                        loaded: (_) {
                          const SnackBar(content: Text("Donnees chargee"));
                        });
                  },
                  builder: (context, state) {
                    print(state.toString());
                    return state.maybeWhen(
                        orElse: () => Container(),
                        loaded: (value) => ListView.builder(
                            itemCount: value.length,
                            itemBuilder: (context, int index) {
                              return Container(
                                margin: const EdgeInsets.all(2),
                                height: 60,
                                decoration: BoxDecoration(
                                    borderRadius: BorderRadius.circular(20),
                                    border: Border.all(
                                        color: Colors.black, width: 0.4)),
                                child: Padding(
                                  padding: const EdgeInsets.all(8.0),
                                  child: Row(
                                    children: [
                                      Text(
                                        '${value[index].dateFile.day}- ${value[index].dateFile.month} ',
                                        style: GoogleFonts.inter(),
                                      ),
                                      const SizedBox(
                                        width: 20,
                                      ),
                                      MaterialButton(
                                        elevation: 10,
                                        onPressed: () {
                                          Navigator.push(
                                              context,
                                              MaterialPageRoute(
                                                  builder: (context) =>
                                                      PdfPageView(
                                                        url: value[index].link,
                                                      )));
                                        },
                                        child: Text(
                                          value[index].name,
                                          style: GoogleFonts.inter(),
                                        ),
                                      )
                                    ],
                                  ),
                                ),
                              );
                            }));
                  },
                ),
              )
            ]),
      ),
    ));
  }
}
