import {DepartementDto} from './DepartementDto';
import {AdminRowDto} from './AdminRowDto';

export interface OrganisationDto {
	id: string;
	name: string;
  createdAt?: string;
  organisation?: OrganisationDto;
  departments?: DepartementDto[];
  adminRows?: AdminRowDto[];
  space? : SpaceDto
}

export interface SpaceDto {
  id: string;
  name: string;
}

