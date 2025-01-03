import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TextFieldCompView extends StatelessWidget {
  TextFieldCompView({
    super.key,
    this.hint,
    required this.controller,
    this.maxLine
  });
  String? hint = "";
  TextEditingController controller;
  int? maxLine = 1;

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      maxLines: maxLine,
      controller: controller,
      cursorColor: Colors.black,
      decoration:  InputDecoration(
        focusedBorder: const OutlineInputBorder(
          borderSide: BorderSide(color: Colors.black, width: 3),
          borderRadius: BorderRadius.all(Radius.circular(20)),
        ),
        border: const OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(20))),
        labelText: hint,
        labelStyle: const TextStyle(color: Colors.black),
      ),
    );
  }
}
