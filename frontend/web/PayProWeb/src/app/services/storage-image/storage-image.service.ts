import { Injectable } from '@angular/core';
import {environment} from '../../environement/env';
import { createClient } from '@supabase/supabase-js';

@Injectable({
  providedIn: 'root'
})
export class StorageImageService {

  supabase = createClient(environment.SUPABASE_URL, environment.SUPABASE_KEY);

  constructor() {}

  async storeFile(file: File) {
    const r = await this.supabase.storage.from('image').upload(file.name, file);
    if(r.error) {
      if(r.error.message.includes('The resource already exists')) {
        return this.supabase.storage.from('image').getPublicUrl(file.name);
      }
    }
    return r.error
      ? console.error(r.error)
      : await this.supabase.storage.from('image').getPublicUrl(file.name);
  }
}
