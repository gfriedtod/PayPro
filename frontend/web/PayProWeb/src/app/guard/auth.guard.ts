import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {TokenService} from '../services/token/token.service';

export const authGuard: CanActivateFn = (route, state) => {
  const router: Router = inject(Router);
  const tokenStorage: TokenService = inject(TokenService);
  if (tokenStorage.getToken() && !tokenStorage.isExpired()) {
    return true;
  } else {
    tokenStorage.removeToken();
    router.navigate(['login']).then();
    return false;
  }
};
