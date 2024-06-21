import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './components/footer/footer.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { TableComponent } from './components/table/table.component';
import { StepperComponent } from './components/stepper/stepper.component';
import { CitoyenService } from 'src/app/services/citoyen.service';
import { ReactiveFormsModule } from '@angular/forms';
@NgModule({
  declarations: [
    FooterComponent,
    SidebarComponent,
    HeaderComponent,
    NotFoundComponent,
    TableComponent,
    StepperComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule
  ],
  exports: [
    FooterComponent,
    SidebarComponent,
    HeaderComponent,
    NotFoundComponent,
    TableComponent,
    StepperComponent
  ],
  providers: [
    CitoyenService
  ]
})
export class SharedModule { }
