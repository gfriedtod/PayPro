import { Injectable } from '@angular/core';

const TOKEN_KEY = 'token';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  public saveToken(token: string) {
    this.removeToken();
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY);
  }

  public removeToken() {
    return localStorage.removeItem(TOKEN_KEY);
  }

  public parseJwt(token: string | null) {
    if (!token) {
      return null;
    }
    const base64Url = token!.split('.')[1]; // Récupération du payload
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split('')
        .map(function (c) {
          return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        })
        .join(''),
    );
    return JSON.parse(jsonPayload);
  }

  public getTokenExpirationDate() {
    const token = this.getToken();
    if (!token) {
      return null;
    }
    const payload = this.parseJwt(token);
    return new Date(payload.exp * 1000);
  }

  public isExpired() {
    const token = this.getToken();
    if (!token) {
      return true;
    }
    return this.getTokenExpirationDate()!.getTime() < Date.now();
  }

  public getUserId() {
    const token = this.getToken();
    if (!token) {
      return null;
    }
    const payload = this.parseJwt(token);
    return payload.jti;
  }

  public getUserIdByToken(token: string) {
    const payload = this.parseJwt(token);
    return payload.jti;
  }

  isValid() {


  }
}
