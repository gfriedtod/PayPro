import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AdminRowDto} from '../../model/AdminRowDto';
import {environment} from '../../environement/env';

@Injectable({
  providedIn: 'root'
})
export class AdminRowService {

  constructor(private  http: HttpClient) { }

  save(row : AdminRowDto){
    return this.http.post<AdminRowDto>(`${environment.api}/adminRow`, row);
  }
}
