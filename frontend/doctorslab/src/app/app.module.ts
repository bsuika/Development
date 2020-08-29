import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/layout/common/header/header.component';
import { FooterComponent } from './components/layout/common/footer/footer.component';
import { LeftmenuComponent } from './components/layout/common/leftmenu/leftmenu.component';
import { TestlistComponent } from './components/layout/site/testlist/testlist.component';
import { CartComponent } from './components/layout/site/cart/cart.component';
import { LoginComponent } from './components/layout/site/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LeftmenuComponent,
    TestlistComponent,
    CartComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
