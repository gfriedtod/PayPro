import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GeneratePasswordService {

  constructor() { }

  generatePassword(){
    const letters = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ#$%@'; // Lettres majuscules et minuscules
    let password = '';
    for (let i = 0; i < 12; i++) {
      const randomIndex = Math.floor(Math.random() * letters.length); // Sélectionne un index aléatoire
      password += letters[randomIndex]; // Ajoute la lettre correspondante
    }
    return password;
  }
}
