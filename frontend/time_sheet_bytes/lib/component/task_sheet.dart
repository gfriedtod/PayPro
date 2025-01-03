import 'package:date_format/date_format.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:time_range/time_range.dart';
import 'package:time_sheet_bytes/component/text_field_comp.dart';
import 'package:time_sheet_bytes/services/task/task_cubit.dart';

import '../model/task.dart';

class TaskSheet extends StatefulWidget {
  TaskSheet({super.key, this.task, this.index}) {
    if (task != null) {
      nameController.text = task!.name;
      descriptionController.text = task!.description;
      timeRangeResult = TimeRangeResult(
        TimeOfDay(
            hour: (int.tryParse(task!.start.split(':')[0]))!,
            minute: int.parse(task!.start.split(':')[1])),
        TimeOfDay(
            hour: (int.tryParse(task!.end.split(':')[0]))!,
            minute: int.parse(task!.end.split(':')[1])),
      );
      print(timeRangeResult?.end.hour);
      print(timeRangeResult?.end.minute);
    }
  }
  TextEditingController nameController = TextEditingController();
  TextEditingController descriptionController = TextEditingController();
  TimeRangeResult? timeRangeResult = TimeRangeResult(
    const TimeOfDay(hour: 8, minute: 30),
    const TimeOfDay(hour: 20, minute: 00),
  );
  final _controller = TextEditingController();

  Task? task;

  @override
  State<TaskSheet> createState() => _TaskSheetState(task);

  int? index;

  get controller => _controller;
}

class _TaskSheetState extends State<TaskSheet> {
  _TaskSheetState(Task? task) {
    if (task != null) {
      nameController.text = task!.name;
      descriptionController.text = task!.description;
      timeRangeResult = TimeRangeResult(
        TimeOfDay(
            hour: (int.tryParse(task!.start.split(':')[0]))!,
            minute: int.parse(task!.start.split(':')[1])),
        TimeOfDay(
            hour: (int.tryParse(task!.end.split(':')[0]))!,
            minute: int.parse(task!.end.split(':')[1])),
      );
      print(timeRangeResult?.end.hour);
      print(timeRangeResult?.end.minute);
    }
  }
  TextEditingController nameController = TextEditingController();
  TextEditingController descriptionController = TextEditingController();
  TimeRangeResult? timeRangeResult = TimeRangeResult(
    const TimeOfDay(hour: 8, minute: 30),
    const TimeOfDay(hour: 20, minute: 00),
  );

  @override
  Widget build(BuildContext context) {
    return BlocConsumer<TaskCubit, TaskState>(
      listenWhen: (previous, current) => previous != current,
      listener: (context, state) {
        state.maybeMap(
          orElse: () {},
          added: (e) => {
            Navigator.pop(context),
            if (widget.index != null)
              {
                Navigator.pop(context),
              }
          },
        );
      },
      builder: (context, state) {
        Size size = MediaQuery.of(context).size;
        return state.maybeMap(
          orElse: () => Container(),
          initial: (_) => BottomSheet(
            backgroundColor: Colors.white,
            onClosing: () {
              widget.task = null;
            },
            builder: (context) {
              Size size = MediaQuery.of(context).size;
              return SingleChildScrollView(
                child: Padding(
                  padding: const EdgeInsets.all(20.0),
                  child: SizedBox(
                    height: size.height *0.9,
                    child: Column(
                      children: [
                        Row(
                          children: [
                            Text(
                              "New Task",
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
                          hint: "Task name",
                        ),
                        const SizedBox(
                          height: 20,
                        ),
                        TextFieldCompView(
                          controller: descriptionController,
                          hint: "Description",
                          maxLine: 5,
                        ),
                        const SizedBox(
                          height: 20,
                        ),
                        TimeRange(
                          fromTitle: Text(
                            'From',
                            style: GoogleFonts.poppins(
                                fontSize: 18, color: Colors.grey),
                          ),
                          toTitle: Text(
                            'To',
                            style: GoogleFonts.poppins(
                                fontSize: 18, color: Colors.grey),
                          ),
                          titlePadding: 20,
                          textStyle: GoogleFonts.poppins(
                              fontWeight: FontWeight.normal, color: Colors.black87),
                          activeTextStyle: GoogleFonts.poppins(
                              fontWeight: FontWeight.bold, color: Colors.white),
                          borderColor: Colors.black,
                          backgroundColor: Colors.transparent,
                          activeBackgroundColor: Colors.black,
                          firstTime: const TimeOfDay(hour: 8, minute: 30),
                          lastTime: const TimeOfDay(hour: 20, minute: 00),
                          initialRange: timeRangeResult,
                          timeStep: 10,
                          timeBlock: 30,
                          onRangeCompleted: (range) => setState(() {
                            timeRangeResult = range;
                          }),
                        ),
                        const Spacer(),
                        SizedBox(
                          width: double.infinity,
                          height: 60,
                          child: MaterialButton(
                            shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(15)),
                            onPressed: () {
                              context.read<TaskCubit>().addTask(buildTask());
                              widget.task = null;
                            },
                            color: Colors.black,
                            child: Text(
                              "Save",
                              style: GoogleFonts.poppins(
                                  fontWeight: FontWeight.w600, color: Colors.white),
                            ),
                          ),
                        ),
                        const Spacer(),
                      ],
                    ),
                  ),
                ),
              );
            },
          ),
          added: (e) => BottomSheet(
            backgroundColor: Colors.white,
            onClosing: () {},
            builder: (context) {
              return SingleChildScrollView(
                child: Padding(
                  padding: const EdgeInsets.all(20.0),
                  child: SizedBox(
                    height: size.height *0.9,

                    child: Column(
                      children: [
                        Row(
                          children: [
                            Text(
                              "New Task",
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
                          hint: "Task name",
                        ),
                        const SizedBox(
                          height: 20,
                        ),
                        TextFieldCompView(
                          controller: descriptionController,
                          hint: "Description",
                          maxLine: 5,
                        ),
                        const SizedBox(
                          height: 20,
                        ),
                        TimeRange(
                          fromTitle: Text(
                            'From',
                            style: GoogleFonts.poppins(
                                fontSize: 18, color: Colors.grey),
                          ),
                          toTitle: Text(
                            'To',
                            style: GoogleFonts.poppins(
                                fontSize: 18, color: Colors.grey),
                          ),
                          titlePadding: 20,
                          textStyle: GoogleFonts.poppins(
                              fontWeight: FontWeight.normal, color: Colors.black87),
                          activeTextStyle: GoogleFonts.poppins(
                              fontWeight: FontWeight.bold, color: Colors.white),
                          borderColor: Colors.black,
                          backgroundColor: Colors.transparent,
                          activeBackgroundColor: Colors.black,
                          firstTime: const TimeOfDay(hour: 8, minute: 30),
                          lastTime: const TimeOfDay(hour: 20, minute: 00),
                          initialRange: timeRangeResult,
                          timeStep: 10,
                          timeBlock: 30,
                          onRangeCompleted: (range) => setState(() {
                           timeRangeResult = range;
                          }),
                        ),
                        const Spacer(),
                        SizedBox(
                          width: double.infinity,
                          height: 60,
                          child: MaterialButton(
                            shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(15)),
                            onPressed: () {
                              context.read<TaskCubit>().addTask(buildTask());
                              widget.task = null;
                            },
                            color: Colors.black,
                            child: Text(
                              "Save",
                              style: GoogleFonts.poppins(
                                  fontWeight: FontWeight.w600, color: Colors.white),
                            ),
                          ),
                        ),
                        const Spacer(),
                      ],
                    ),
                  ),
                ),
              );
            },
          ),
          initUpdate: (e) => BottomSheet(
            backgroundColor: Colors.white,
            onClosing: () {},
            builder: (context) {
              return Padding(
                padding: const EdgeInsets.all(20.0),
                child: Column(
                  children: [
                    const SizedBox(height: 50),
                    Row(
                      children: [
                        Text(
                          "Update Task",
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
                      hint: "Task name",
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    TextFieldCompView(
                      controller: descriptionController,
                      hint: "Description",
                      maxLine: 5,
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    TimeRange(
                      fromTitle: Text(
                        'From',
                        style: GoogleFonts.poppins(
                            fontSize: 18, color: Colors.grey),
                      ),
                      toTitle: Text(
                        'To',
                        style: GoogleFonts.poppins(
                            fontSize: 18, color: Colors.grey),
                      ),
                      titlePadding: 20,
                      textStyle: GoogleFonts.poppins(
                          fontWeight: FontWeight.normal, color: Colors.black87),
                      activeTextStyle: GoogleFonts.poppins(
                          fontWeight: FontWeight.bold, color: Colors.white),
                      borderColor: Colors.black,
                      backgroundColor: Colors.transparent,
                      activeBackgroundColor: Colors.black,
                      firstTime: const TimeOfDay(hour: 8, minute: 30),
                      lastTime: const TimeOfDay(hour: 20, minute: 00),
                      initialRange: timeRangeResult,
                      timeStep: 10,
                      timeBlock: 30,
                      onRangeCompleted: (range) => setState(() {
                        timeRangeResult = range;
                      }),
                    ),
                    const Spacer(),
                    SizedBox(
                      width: double.infinity,
                      height: 60,
                      child: MaterialButton(
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(15)),
                        onPressed: () {
                          context
                              .read<TaskCubit>()
                              .updateTask(buildTask(), widget.index!);
                        },
                        color: Colors.black,
                        child: Text(
                          "Update",
                          style: GoogleFonts.poppins(
                              fontWeight: FontWeight.w600, color: Colors.white),
                        ),
                      ),
                    ),
                    const Spacer(),
                  ],
                ),
              );
            },
          ),
        );
      },
    );
  }

  Task buildTask() {
    return Task(
      name: nameController.text,
      description: descriptionController.text,
      start:
          '${timeRangeResult!.start.hour}:${timeRangeResult!.start.minute}',
      end:
          '${timeRangeResult!.end.hour}:${timeRangeResult!.end.minute}',
      date: formatDate(DateTime.now(), [yyyy, '-', mm, '-', dd]),
    );
  }
}
