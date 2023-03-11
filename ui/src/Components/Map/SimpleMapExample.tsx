import React, { useState } from 'react';
import GoogleMapReact from 'google-map-react';
import Marker from './Marker';

const SimpleMap = (props: any) => {
    const [center, setCenter] = useState({lat: 41.64548781057474, lng: 41.62573272456442 });
    const [zoom, setZoom] = useState(15);
    return (
        <div style={{ height: '100vh', position: 'relative' }}>
            <GoogleMapReact
                bootstrapURLKeys={{ key: 'AIzaSyAJ7QA6FkbHEVQXQlUH0rq2nuS0Khv1HUc' }}
                defaultCenter={center}
                defaultZoom={zoom}
            >
                <Marker
                    lat={41.64548781057474}
                    lng={41.62573272456442}
                />
            </GoogleMapReact>
        </div>
    );
}

export default SimpleMap;