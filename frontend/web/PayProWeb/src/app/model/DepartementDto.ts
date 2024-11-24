import {OrganisationDto} from './OrganisationDto';

export interface DepartementDto {
  id: string;
  createdAt: string;
  name: string;
  organisation: OrganisationDto; // Data
}
