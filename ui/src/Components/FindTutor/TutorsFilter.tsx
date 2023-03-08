import {Button, Container, Grid, MenuItem, TextField} from "@mui/material";
import {useFindTutorStore} from "../../pages/FIndTutor/findTutorStore";

interface ConvType {
    convTypeId: number;
    description: string;
}

const fieldWidth = {
    width: '8vw'
}

const gridMarginRight = {
    mr: 6
}

export const TutorsFilter = () => {
    const getTutors = useFindTutorStore(state => state.getTutors);
    const clearTutors = useFindTutorStore(state => state.clearTutors);
    const setMinPrice = useFindTutorStore(state => state.setMinPrice);
    const minPrice = useFindTutorStore(state => state.minPrice);
    const setMaxPrice = useFindTutorStore(state => state.setMaxPrice);
    const maxPrice = useFindTutorStore(state => state.maxPrice);
    const clearLastTutorId = useFindTutorStore(state => state.clearLastTutorId);

    const convTypes: ConvType[] = [
        {convTypeId: 1, description: "Offline"},
        {convTypeId: 1, description: "Online"}
    ]


    return (
        <>
            <Container maxWidth="xl" sx={{position: 'fixed', backgroundColor: 'white', pb: 2}}>
                <Grid container sx={{mt: 10, ml: 10}} alignItems="flex-end">
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            label="Min Price $"
                            variant="standard"
                            value={minPrice}
                            onChange={e => setMinPrice(Number(e.target.value))}
                            sx={fieldWidth}/>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            label="Max Price $"
                            value={maxPrice}
                            onChange={e => setMaxPrice(Number(e.target.value))}
                            variant="standard"
                            sx={fieldWidth}/>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField label="Country" variant="standard" sx={fieldWidth}/>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            select
                            variant="standard"
                            label="City"
                            sx={fieldWidth}
                        >{convTypes.map((conv) => (
                            <MenuItem key={conv.convTypeId}>{conv.description}</MenuItem>
                        ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            select
                            variant="standard"
                            label="Type"
                            sx={fieldWidth}
                        >{convTypes.map((conv) => (
                            <MenuItem key={conv.convTypeId}>{conv.description}</MenuItem>
                        ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            select
                            variant="standard"
                            label="Language"
                            sx={fieldWidth}
                        >{convTypes.map((conv) => (
                            <MenuItem key={conv.convTypeId}>{conv.description}</MenuItem>
                        ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            select
                            variant="standard"
                            label="Min Level"
                            sx={fieldWidth}
                        >{convTypes.map((conv) => (
                            <MenuItem key={conv.convTypeId}>{conv.description}</MenuItem>
                        ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <Button
                            variant="outlined"
                            onClick={() => {
                                clearTutors();
                                clearLastTutorId();
                                getTutors();
                            }
                            }>Apply</Button>
                    </Grid>
                </Grid>
            </Container>
        </>
    )
}