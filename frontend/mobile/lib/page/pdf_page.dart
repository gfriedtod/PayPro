import 'package:easy_pdf_viewer/easy_pdf_viewer.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:syncfusion_flutter_pdfviewer/pdfviewer.dart';

class PdfPageView extends StatelessWidget {
  const PdfPageView({super.key, required this.url});
  final String url;

  @override
  Widget build(BuildContext context) {
    return  Scaffold(
        body: Container(
            child: FutureBuilder(
              future: PDFDocument.fromURL(url),
              builder: (context,snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return const Center(child: CircularProgressIndicator(
                    color: Colors.black,
                    strokeWidth: 8,
                  ));

                }
                if (snapshot.data != null) {
                  return PDFViewer( document: snapshot.data!,);
                }
                return Container();
              }
            ),
          ),);
  }
}
