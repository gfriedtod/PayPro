import {OrganisationDto} from './OrganisationDto';
import {AdminDto} from './AdminDto';

export interface AdminRowDto{
  id?: number;
  organisation:OrganisationDto;
  admin: AdminDto
}
