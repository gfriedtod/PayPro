import {OrganisationDto} from '../model/OrganisationDto';

export const environment = {
  production: false,
  api: 'https://localhost/modulith/v1',
  SUPABASE_URL: 'https://lriiektzcaxczrhzymgg.supabase.co',
  SUPABASE_KEY:
    'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImxyaWlla3R6Y2F4Y3pyaHp5bWdnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjE0NTI4OTksImV4cCI6MjAzNzAyODg5OX0.oJXiQ-XyA6Cr3DTc2sewL0-028-OZ2SYxTIneuqKAB0',
}


export const copy= (s : string)=> {
  navigator.clipboard.writeText(s).then(r =>  console.log(r));
}
