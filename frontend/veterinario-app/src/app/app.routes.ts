import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AnimalListComponent } from './components/animal/animal-list.component';
import { InseminacaoListComponent } from './components/inseminacao/inseminacao-list.component';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'animais', component: AnimalListComponent },
  { path: 'inseminacoes', component: InseminacaoListComponent },
];
