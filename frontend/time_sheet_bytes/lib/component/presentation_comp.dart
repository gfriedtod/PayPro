import 'package:flutter/cupertino.dart';
import 'package:google_fonts/google_fonts.dart';

class PresentationView extends StatelessWidget {
  const PresentationView({
    super.key,
    required this.title,
    required this.iconData,
    required this.value,
  });

  final String title;
  final IconData iconData;
  final String value;
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          title,
          style: GoogleFonts.poppins(fontWeight: FontWeight.w600, fontSize: 18),
        ),
        const SizedBox(
          height: 10,
        ),
        Row(
          children: [
            Icon(iconData),
            const SizedBox(
              width: 10,
            ),
            Flexible(
              child: Text(
                value,
                style: GoogleFonts.poppins(),
              ),
            )
          ],
        ),
        const SizedBox(
          height: 20,
        ),
      ],
    );
  }
}
