import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environement/env';
import {FileDto} from '../../model/FileDto';
import {UserDto} from '../../model/UserDto';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private http: HttpClient) { }

  saveDoc(fileDto: FileDto) {
    return this.http.post<FileDto>(`${environment.api}/file`, fileDto)
  }

  fetchDocs(user: UserDto) {
    return this.http.get<FileDto[]>(`${environment.api}/file/user/${user.id}`)
  }
}
