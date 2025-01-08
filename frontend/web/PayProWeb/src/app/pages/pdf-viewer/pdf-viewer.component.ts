import {Component, Input} from '@angular/core';
import {NgxDocViewerModule} from 'ngx-doc-viewer';

@Component({
  selector: 'app-pdf-viewer',
  standalone: true,
  imports: [
    NgxDocViewerModule
  ],
  templateUrl: './pdf-viewer.component.html',
  styleUrl: './pdf-viewer.component.css'
})
export class PdfViewerComponent {
 @Input() doc!:string;

}
