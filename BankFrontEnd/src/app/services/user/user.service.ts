
import { Injectable } from '@angular/core';
import { User } from '../../models/User';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { UtilityService } from '../utility/utility.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  searchOption=[];
  public userData: User[] | undefined;
  jwtToken = sessionStorage.getItem('JWT');

  headers = new HttpHeaders().set('Content-type', 'application/json')
                             .set('authorization', this.jwtToken || '');
  constructor(private httpCli: HttpClient, private utilityService: UtilityService, private router: Router) {}

  getAllUsers(): Observable<any> {
    return this.httpCli.get<any>(`${this.utilityService.getServerDomain()}/user`)
  }

  getUserById(id: number): Observable<any> {
    this.setHeaders();
    return this.httpCli.get<any>(`${this.utilityService.getServerDomain()}/user/${id}`, {'headers': this.headers});
  }

  register(user: User) : Observable<any>{
    return this.httpCli.post<any>(`${this.utilityService.getServerDomain()}/user`, user);
  }

  updateUser(user: User) : Observable<any> {
    this.setHeaders();
    return this.httpCli.patch<any>(`${this.utilityService.getServerDomain()}/user/updateUser`, user);
  }

  login(user: User) : Observable<any> {
    return this.httpCli.post<any>(`${this.utilityService.getServerDomain()}/user/login`, user);
  }

   logout() {
    sessionStorage.clear();
    this.router.navigateByUrl('');
  }

  setHeaders(): void {
    this.jwtToken = sessionStorage.getItem('JWT');
    this.headers = new HttpHeaders().set('Content-type', 'application/json')
                             .set('authorization', this.jwtToken || '');
  }
}
