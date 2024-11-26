import {Component, inject, signal} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {HlmButtonDirective} from '../../components/lib/ui-button-helm/src';
import {
  HlmCardContentDirective,
  HlmCardFooterDirective,
  HlmCardHeaderDirective
} from '../../components/lib/ui-card-helm/src';
import {HlmCheckboxComponent} from '../../components/lib/ui-checkbox-helm/src';
import {HlmInputDirective} from '../../components/lib/ui-input-helm/src';
import {HlmLabelDirective} from '../../components/lib/ui-label-helm/src';
import {Router, RouterLink} from '@angular/router';
import {TokenService} from '../../services/token/token.service';
import {AuthService} from '../../services/auth/auth.service';
import {LoginRequest} from '../../model/LoginRequest';
import {HlmSpinnerComponent} from '../../components/lib/ui-spinner-helm/src';
import {ToastComponent} from '../../components/toast/toast.component';
import {timer} from 'rxjs';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    HlmButtonDirective,
    HlmCardContentDirective,
    HlmCardFooterDirective,
    HlmCardHeaderDirective,
    HlmCheckboxComponent,
    HlmInputDirective,
    HlmLabelDirective,
    ReactiveFormsModule,
    RouterLink,
    HlmSpinnerComponent,
    ToastComponent
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  login: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
  });

  tokenService : TokenService = inject(TokenService)
  auth:AuthService = inject(AuthService)
  router:Router = inject(Router)

  perform = signal(false)
  visibleToast = signal(false);
  toastState = signal(true);
  async loginReq() {

    function sleep(number: number) {
      return new Promise((resolve) => setTimeout(resolve, number));
    }

    if (this.login.valid) {
      this.perform.set(true);
     let  loginRequest : LoginRequest  = {
       email: this.login.value.email,
       password: this.login.value.password
      };
      (await this.auth.login(loginRequest)).subscribe({
        next: (data) => {
          this.tokenService.saveToken(data.token);
          localStorage.setItem('user', JSON.stringify(data.user));
          this.perform.set(false);

        },
        error: (error) => {
          console.log(error);
          this.perform.set(false);
          this.toastState.set(false);
          this.visibleToast.set(true);
          timer(1000).subscribe(() => this.visibleToast.set(false));
        },
        complete: () => {
          this.perform.set(false);
          this.router.navigate(['/general']);


        }
      })

    }
  }

}
