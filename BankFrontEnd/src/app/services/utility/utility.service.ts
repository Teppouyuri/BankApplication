import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilityService {
  
  private _serverDomain: string = "http://localhost:8080"

  constructor() { }

  getServerDomain(){
    return this._serverDomain;
  }
}
