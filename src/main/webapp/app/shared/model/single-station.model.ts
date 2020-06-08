export interface ISingleStation {
  id?: number;
  name?: string;
  url?: string;
  license?: string;
  userLogin?: string;
  userId?: number;
}

export class SingleStation implements ISingleStation {
  constructor(
    public id?: number,
    public name?: string,
    public url?: string,
    public license?: string,
    public userLogin?: string,
    public userId?: number
  ) {}
}
