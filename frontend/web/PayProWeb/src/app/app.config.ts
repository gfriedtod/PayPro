import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideIcons} from '@ng-icons/core';
import {lucideArrowRight, lucideCheck, lucideChevronDown} from '@ng-icons/lucide';
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptors} from '@angular/common/http';
import {authInterceptor} from './interceptor/auth.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes), provideIcons(
    {
      lucideChevronDown,
      lucideArrowRight,
      lucideCheck
    },

  ),
    provideHttpClient(
      withInterceptors([authInterceptor])
    ),
    { provide: HTTP_INTERCEPTORS, useValue: authInterceptor, multi: true }]
};
