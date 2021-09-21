import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';


import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';
import { Ng2PaginationModule} from 'ng2-pagination';

import { AppComponent } from './app.component';

import {
  AgmCoreModule
} from '@agm/core';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { ViewUsersComponent } from './user-profile/view-users/view-users.component';
import { UpdateUsersComponent } from './user-profile/update-users/update-users.component';
import { ItemService } from './service/item.service';
import { OrderService } from './service/orders.service';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatRippleModule} from '@angular/material/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatTooltipModule} from '@angular/material/tooltip';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './components/helpers/auth.interceptor';
import { ErrorInterceptor } from './components/helpers/error.interceptor';
import { AuthenticationService } from './service/authentication.service';
import { AuthGuard } from './service/auth.guard.service';


@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ComponentsModule,
    RouterModule,
      MatButtonModule,
      MatRippleModule,
      MatFormFieldModule,
      MatInputModule,
      MatSelectModule,
      MatTooltipModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({

    })
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,

  ],
  providers: [
    AuthenticationService,
    OrderService,
    ItemService,
    AuthGuard,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
