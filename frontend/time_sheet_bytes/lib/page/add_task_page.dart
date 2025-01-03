import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:date_format/date_format.dart';
import 'package:modal_bottom_sheet/modal_bottom_sheet.dart';
import 'package:r_nav_n_sheet/r_nav_n_sheet.dart';
import 'package:time_sheet_bytes/component/presentation_comp.dart';
import 'package:time_sheet_bytes/component/task_sheet.dart';
import 'package:time_sheet_bytes/model/task.dart';
import 'package:time_sheet_bytes/services/task/task_cubit.dart';

class AddTaskPageView extends StatefulWidget {
  const AddTaskPageView({super.key, required this.sheet});
  final Widget sheet;
  @override
  State<AddTaskPageView> createState() => _AddTaskPageViewState();
}

class _AddTaskPageViewState extends State<AddTaskPageView> {
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return SizedBox(
      height: double.infinity,
      child: Scaffold(
          backgroundColor: Colors.white,
          floatingActionButton: FloatingActionButton.extended(
            backgroundColor: Colors.black,
            onPressed: () {
              context.read<TaskCubit>().sendMailjetEmail();
            },
            label: BlocConsumer<TaskCubit, TaskState>(
              listener: (context, state) {
                // TODO: implement listener
              },
              builder: (context, state) {
                return state.maybeWhen(
                    orElse: () => Text(
                          'Send email',
                          style: GoogleFonts.poppins(color: Colors.white),
                        ),
                    loading: () => const Padding(
                          padding: EdgeInsets.all(8.0),
                          child: CircularProgressIndicator(
                            color: Colors.white,
                          ),
                        ));
              },
            ),
          ),
          appBar: AppBar(
            backgroundColor: Colors.white,
            leadingWidth: double.infinity,
            toolbarHeight: 100,
            leading: Padding(
              padding: const EdgeInsets.all(15.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  Text(
                    "Time Sheet",
                    style: GoogleFonts.poppins(
                        color: Colors.black,
                        fontSize: 25,
                        fontWeight: FontWeight.w600),
                  ),
                  const SizedBox(height: 5),
                  Text(
                    "The best way to manage your tasks",
                    style: GoogleFonts.poppins(
                        color: const Color(0xff7b7979),
                        fontSize: 15,
                        fontWeight: FontWeight.w600),
                  ),
                ],
              ),
            ),
          ),
          body: SingleChildScrollView(
              child: Padding(
            padding: const EdgeInsets.all(10.0),
            child: Center(
                child: SizedBox(
              width: size.width * 0.95,
              child: Column(children: [
                const SizedBox(height: 30),
                BlocConsumer<TaskCubit, TaskState>(
                  listener: (context, state) {
                    // TODO: implement listener
                    state.maybeMap(
                      orElse: () {},
                      initUpdate: (tasks) {},
                      error: (val) {
                        ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                            backgroundColor: Colors.red,
                            content: Text(val.message,
                                style: GoogleFonts.poppins(
                                  color: Colors.white,
                                ))));
                      },
                    );
                  },
                  builder: (context, state) {
                    print(state);
                    return state.maybeWhen(
                      initial: (tasks) => const NoTaskComp(),
                      loading: () => const CircularProgressIndicator(),
                      loaded: (tasks) => const NoTaskComp(),
                      error: (message, tasks) {
                        if (tasks!.isEmpty) {
                          return const NoTaskComp();
                        }
                        return Column(
                          children: [
                            SizedBox(
                              height: 600,
                              child: ListView.builder(
                                shrinkWrap: true,
                                itemCount: tasks.length,
                                itemBuilder: (context, index) {
                                  return Column(
                                    children: [
                                      TaskComp(
                                        task: tasks[index],
                                        sheet: widget.sheet,
                                        index: index,
                                      ),
                                      const SizedBox(height: 10),
                                    ],
                                  );
                                },
                              ),
                            ),
                          ],
                        );
                      },
                      added: (tasks) {
                        if (tasks.isEmpty) {
                          return const NoTaskComp();
                        }
                        return Column(
                          children: [
                            SizedBox(
                              height: 600,
                              child: ListView.builder(
                                shrinkWrap: true,
                                itemCount: tasks.length,
                                itemBuilder: (context, index) {
                                  return Column(
                                    children: [
                                      TaskComp(
                                        task: tasks[index],
                                        sheet: widget.sheet,
                                        index: index,
                                      ),
                                      const SizedBox(height: 10),
                                    ],
                                  );
                                },
                              ),
                            ),
                          ],
                        );
                      },
                      initUpdate: (tasks) => Column(
                        children: [
                          SizedBox(
                            height: 600,
                            child: ListView.builder(
                              shrinkWrap: true,
                              itemCount: tasks.length,
                              itemBuilder: (context, index) {
                                return Column(
                                  children: [
                                    TaskComp(
                                      task: tasks[index],
                                      sheet: widget.sheet,
                                      index: index,
                                    ),
                                    const SizedBox(height: 10),
                                  ],
                                );
                              },
                            ),
                          ),
                        ],
                      ),
                      orElse: () => const NoTaskComp(),
                    );
                  },
                ),
              ]),
            )),
          ))),
    );
  }
}

class NoTaskComp extends StatelessWidget {
  const NoTaskComp({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      color: Colors.white,
      surfaceTintColor: Colors.white,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
      child: Column(
        children: [
          Container(
            width: double.infinity,
            height: 40,
            decoration: const BoxDecoration(
              color: Colors.black,
              borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(20), topRight: Radius.circular(20)),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: SizedBox(
              height: 100,
              child: Column(
                children: [
                  Row(
                    children: [
                      IconButton(
                        style: IconButton.styleFrom(
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(
                                10), // Set the radius here
                          ),
                          backgroundColor: Colors.black,
                        ),
                        onPressed: () {},
                        icon: const Icon(
                          Icons.add,
                          color: Colors.white,
                        ),
                      ),
                      const SizedBox(
                        width: 10,
                      ),
                      Text(
                        "Tap plus to add new task",
                        style: GoogleFonts.poppins(
                            color: Colors.black,
                            fontWeight: FontWeight.w500,
                            fontSize: 18),
                      )
                    ],
                  ),
                  const SizedBox(height: 10),
                  const Divider(),
                  Row(children: [
                    Text(
                      "Add new task",
                      style: GoogleFonts.poppins(
                          color: Color(0xff7b7979),
                          fontWeight: FontWeight.w500,
                          fontSize: 15),
                    ),
                    const Spacer(),
                    Text(
                      formatDate(DateTime.now(), [
                        dd,
                        '-',
                        mm,
                        '-',
                        yyyy // yyyy for 4 digit year
                      ]),
                      style: GoogleFonts.poppins(
                          color: const Color(0xff7b7979),
                          fontWeight: FontWeight.w500,
                          fontSize: 15),
                    ),
                  ])
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}

class TaskComp extends StatefulWidget {
  TaskComp(
      {super.key,
      required this.task,
      required this.sheet,
      required this.index});
  final Task task;
  final Widget sheet;

  int? index;

  @override
  State<TaskComp> createState() => _TaskCompState();
}

class _TaskCompState extends State<TaskComp> {
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        showMaterialModalBottomSheet(
            context: context,
            builder: (context) => TaskDetailView(widget: widget));
      },
      child: Card(
        color: Colors.white,
        surfaceTintColor: Colors.white,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
        child: Column(
          children: [
            Container(
              width: double.infinity,
              height: 40,
              decoration: const BoxDecoration(
                color: Colors.black,
                borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(20),
                    topRight: Radius.circular(20)),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: SizedBox(
                height: 100,
                child: Column(
                  children: [
                    Row(
                      children: [
                        IconButton(
                          style: IconButton.styleFrom(
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(
                                  10), // Set the radius here
                            ),
                            backgroundColor: Colors.black,
                          ),
                          onPressed: () {},
                          icon: const Icon(
                            Icons.task,
                            color: Colors.white,
                          ),
                        ),
                        const SizedBox(
                          width: 10,
                        ),
                        Text(
                          widget.task.name.length >= 25
                              ? '${widget.task.name.substring(0, 25)}...'
                              : widget.task.name,
                          style: GoogleFonts.poppins(
                              color: Colors.black,
                              fontWeight: FontWeight.w500,
                              fontSize: 18),
                        )
                      ],
                    ),
                    const SizedBox(height: 10),
                    const Divider(),
                    Row(children: [
                      Text(
                        '${widget.task.start} - ${widget.task.end}',
                        style: GoogleFonts.poppins(
                            color: const Color(0xff7b7979),
                            fontWeight: FontWeight.w500,
                            fontSize: 15),
                      ),
                      const Spacer(),
                      Text(
                        widget.task.date,
                        style: GoogleFonts.poppins(
                            color: const Color(0xff7b7979),
                            fontWeight: FontWeight.w500,
                            fontSize: 15),
                      ),
                    ])
                  ],
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}

class TaskDetailView extends StatelessWidget {
  const TaskDetailView({
    super.key,
    required this.widget,
  });

  final TaskComp widget;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          backgroundColor: Colors.white,
          leadingWidth: double.infinity,
          toolbarHeight: 100,
          leading: Padding(
            padding: const EdgeInsets.all(15.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                Text(
                  "Task Details",
                  style: GoogleFonts.poppins(
                      color: Colors.black,
                      fontSize: 25,
                      fontWeight: FontWeight.w600),
                ),
                const SizedBox(height: 5),
              ],
            ),
          ),
        ),
        floatingActionButton: FloatingActionButton.extended(
          backgroundColor: Colors.black,
          onPressed: () {
            context.read<TaskCubit>().initUpdate();
            showModalBottomSheet(
                isScrollControlled: true,
                context: context,
                builder: (context) => TaskSheet(
                      task: widget.task,
                      index: widget.index,
                    ));
          },
          label: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Text(
              "Update",
              style: GoogleFonts.poppins(color: Colors.white),
            ),
          ),
        ),
        body: SingleChildScrollView(
          child: BlocBuilder<TaskCubit, TaskState>(
            builder: (context, state) {
              return Padding(
                  padding: const EdgeInsets.all(10.0),
                  child: Center(
                      child: SizedBox(
                          width: double.infinity,
                          child: Column(children: [
                            const SizedBox(height: 30),
                            PresentationView(
                                title: 'Name',
                                iconData: Icons.task_outlined,
                                value: widget.task.name),
                            PresentationView(
                                title: 'Date',
                                iconData: Icons.calendar_month_outlined,
                                value: widget.task.date),
                            PresentationView(
                                title: 'Time',
                                iconData: Icons.access_time,
                                value:
                                    '${widget.task.start} - ${widget.task.end}'),
                            PresentationView(
                                title: 'Description',
                                iconData: Icons.description_outlined,
                                value: widget.task.description),
                          ]))));
            },
          ),
        ));
  }
}
