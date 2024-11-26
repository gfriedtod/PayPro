import { HttpInterceptorFn } from '@angular/common/http';
import {inject} from '@angular/core';
import {TokenService} from '../services/token/token.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const tokenService = inject(TokenService);
  let authReq = req;
  const token = tokenService.getToken();
  if (token != null && !tokenService.isExpired()) {
    authReq = req.clone({
      headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token),
    });
  }else {
    tokenService.removeToken();
  }
  return next(authReq);
};


const TOKEN_HEADER_KEY = 'Authorization';
