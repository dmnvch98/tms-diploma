import {useState} from "react";
import "cropperjs/dist/cropper.css";
import {Cropper} from "react-cropper";
import FileService from "../../../services/FileService";


type Props = {
    avatarUrl: string;
};

export const FileLoader: React.FC<Props> = ({avatarUrl}) => {
    const [cropper, setCropper] = useState<any>();

    const getCropData = async () => {
        if (cropper) {
            const avatar = await fetch(cropper.getCroppedCanvas().toDataURL())
                .then((res) => res.blob())
                .then((blob) => {
                    return new File([blob], "newAvatar.png", {type: "image/png"});
                });
            const form = new FormData();
            form.append('file', avatar, "newAvatar.png");
            const response = await FileService.uploadAvatar(form);
            console.log(response);
            // let a = document.createElement("a");
            // a.href = URL.createObjectURL(file);
            // a.download = "newAvatar.png";
            // document.body.appendChild(a);
            // a.click();
            // setTimeout(function() {
            //     document.body.removeChild(a);
            //     window.URL.revokeObjectURL(avatarUrl);
            // }, 0);

        }
    };

    return (
        <>
            <Cropper
                src={avatarUrl}
                style={{height: 400, width: 400}}
                initialAspectRatio={4 / 3}
                minCropBoxHeight={100}
                minCropBoxWidth={100}
                guides={false}
                checkOrientation={false}
                onInitialized={(instance) => {
                    setCropper(instance);
                }}
            />
            <button
                className="mt-2 border border-solid border-black py-2 px-4 rounded cursor-pointer"
                onClick={getCropData}
            >
                Crop Image
            </button>
        </>
    )
}