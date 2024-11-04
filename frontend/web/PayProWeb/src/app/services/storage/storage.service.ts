import {Injectable, signal, WritableSignal} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  private data: WritableSignal<any> = signal({});

  constructor() {}

  setData(data: any) {
    this.data.set(data);
    localStorage.setItem(Storage.sharedData, JSON.stringify(data));
  }

  getData() {
    return this.data();
  }
}
export enum Storage {
  sharedData = 'sharedData',
}
