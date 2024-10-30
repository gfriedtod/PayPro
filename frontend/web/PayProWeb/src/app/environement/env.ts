import {OrganisationDto} from '../model/OrganisationDto';

export const environment = {
  production: false,
  api: 'http://localhost:8080',
}

export const fakeOrganisation: OrganisationDto = {
  id: "240a7b05-1200-4a61-bd47-f85c6c04f4e1",
  name: "Bytes-Corp"
}
