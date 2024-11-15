import {Component} from '@angular/core';
import {
  HlmCardContentDirective,
  HlmCardDirective,
  HlmCardFooterDirective,
  HlmCardHeaderDirective
} from '@spartan-ng/ui-card-helm';
import {HlmButtonDirective} from '@spartan-ng/ui-button-helm';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {HlmInputDirective} from '@spartan-ng/ui-input-helm';
import {HlmLabelDirective} from '@spartan-ng/ui-label-helm';
import {HlmCheckboxComponent} from '@spartan-ng/ui-checkbox-helm';

@Component({
  selector: 'app-singup',
  standalone: true,
  imports: [
    HlmCardDirective,
    HlmButtonDirective,
    ReactiveFormsModule,
    HlmInputDirective,
    HlmLabelDirective,
    HlmCardContentDirective,
    HlmCardHeaderDirective,
    HlmCardFooterDirective,
    HlmCheckboxComponent,
  ],
  templateUrl: './singup.component.html',
  styleUrl: './singup.component.css'
})
export class SingupComponent {
  signup: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
    confirmPassword: new FormControl('', [Validators.required, Validators.minLength(6)]),
    acceptTerms: new FormControl(false, [Validators.requiredTrue]),
    phone: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
  });

  singUp() {

  }
}
