import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISingleStation } from 'app/shared/model/single-station.model';

type EntityResponseType = HttpResponse<ISingleStation>;
type EntityArrayResponseType = HttpResponse<ISingleStation[]>;

@Injectable({ providedIn: 'root' })
export class SingleStationService {
  public resourceUrl = SERVER_API_URL + 'api/single-stations';

  constructor(protected http: HttpClient) {}

  create(singleStation: ISingleStation): Observable<EntityResponseType> {
    return this.http.post<ISingleStation>(this.resourceUrl, singleStation, { observe: 'response' });
  }

  update(singleStation: ISingleStation): Observable<EntityResponseType> {
    return this.http.put<ISingleStation>(this.resourceUrl, singleStation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISingleStation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISingleStation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
