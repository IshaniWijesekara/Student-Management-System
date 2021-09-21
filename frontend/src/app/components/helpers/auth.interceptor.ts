import {
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest
  } from '@angular/common/http';
  import { Observable } from 'rxjs/Observable';
  import { Injectable, Injector } from '@angular/core';
  import { Router } from '@angular/router';
import { AuthenticationService } from 'app/service/authentication.service';

  @Injectable()
  export class AuthInterceptor implements HttpInterceptor {
    token: any
    // BUG Angular 4.3: we cannot inject the provider
    // constructor(private authService: AuthService) {}

    // FIX: we need to manually use the injector
    constructor(
      private injector: Injector,
      private router: Router,
      private authenticationService: AuthenticationService
    ) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      // add authorization header with jwt token if available
      const currentUser = this.authenticationService.currentUserValue
      if (currentUser && currentUser.token) {
          request = request.clone({
              setHeaders: {
                  Authorization: `Bearer ${currentUser.token}`,
              }
          });
      }

      return next.handle(request);
     }
  }

