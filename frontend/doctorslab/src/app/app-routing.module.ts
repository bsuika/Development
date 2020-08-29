import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/layout/site/login/login.component';
import { TestlistComponent } from './components/layout/site/testlist/testlist.component';
import { CartComponent } from './components/layout/site/cart/cart.component';


const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'testlist',component:TestlistComponent},
  {path:'cart',component:CartComponent},
  {path:'**',redirectTo:'testlist'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
