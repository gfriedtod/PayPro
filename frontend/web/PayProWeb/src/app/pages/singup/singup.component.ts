import {Component, inject, signal} from '@angular/core';

import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  HlmCardContentDirective,
  HlmCardDirective,
  HlmCardFooterDirective,
  HlmCardHeaderDirective
} from '../../components/lib/ui-card-helm/src';
import {HlmButtonDirective} from '../../components/lib/ui-button-helm/src';
import {HlmInputDirective} from '../../components/lib/ui-input-helm/src';
import {HlmLabelDirective} from '../../components/lib/ui-label-helm/src';
import {HlmCheckboxComponent} from '../../components/lib/ui-checkbox-helm/src';
import {Router, RouterLink} from '@angular/router';
import {AuthService} from '../../services/auth/auth.service';
import {AdminDto} from '../../model/AdminDto';
import {HlmSpinnerComponent} from '../../components/lib/ui-spinner-helm/src';


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
    RouterLink,
    HlmSpinnerComponent,
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
  admin : AdminDto = {} as AdminDto;

  auth : AuthService = inject(AuthService);
  router = inject(Router);

  perform = signal(false);

  async singUp() {

    if (this.signup.valid) {
      this.perform.set(true)
      this.admin = {
        name: this.signup.value.name,
        email: this.signup.value.email,
        password: this.signup.value.password,
        phone: this.signup.value.phone,
        address: this.signup.value.address,
        createdAt: new Date().toISOString(),

      };
      (await this.auth.signUp(this.signup.value))
        .subscribe(
          {
            next: () => {
              this.router.navigate(['/login']);
              this.perform.set(false)
            },
            error: () => {
              this.perform.set(false)
            },
            complete: () => {
              this.perform.set(false)
            }
          }

      );
    }

  }
}
